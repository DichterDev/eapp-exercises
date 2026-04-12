import random
from faker import Faker
import uuid
from time import sleep
import json.decoder
import json
from requests.exceptions import JSONDecodeError
from requests.models import Response
import requests

fake = Faker()

gateway = "http://localhost:8080"
user = f"{gateway}/api/users"
product = f"{gateway}/api/products"
order = f"{gateway}/api/orders"

users = []
products = []
orders = []

def random_uuid() -> str:
    return str(uuid.uuid4())

def random_price() -> float:
    return fake.pyfloat(left_digits=2, right_digits=2, positive=True, min_value=5.0, max_value=99.99)

def handle_response(response: Response, type: str) -> str:
    if response.status_code in [200, 201, 202]:
        try:
            data = response.json()
            id = data["id"]
            print(f"{id}")

            if type == "user":
                users.append(id)
            elif type == "product":
                products.append(id)
            elif type == "order":
                orders.append(id)

            return id
        except JSONDecodeError:
            return ""
    else:
        return f"{response.status_code}"

def register_user(id: str, name: str) -> str:
    payload = {
        "userId": id,
        "name": name
    }
    res = requests.post(f"{user}/register", json=payload)
    return handle_response(res, "user")

def create_product(id: str, name: str, desc: str, price: float, stock: int) -> str:
    payload = {
        "productId": id,
        "name": name,
        "description": desc,
        "price": price,
        "stock": stock
    }
    res = requests.post(f"{product}/create", json=payload)
    return handle_response(res, "product")

def add_cart_item(user_id: str, product_id: str, amount: int):
    payload = {
        "productId": product_id,
        "amount": amount
    }
    requests.post(f"{user}/{user_id}/cart/add", json=payload)

def checkout_cart(user_id: str) -> str:
    res = requests.post(f"{user}/{user_id}/cart/checkout")
    return handle_response(res, "order")

def increase_stock(product_id: str, amount: int):
    res = requests.post(f"{product}/{product_id}/stock/increase", json=amount)
    if res.status_code == 202:
        print("Increased")
    else:
        print("Increase Failed")
    return

def update_price(product_id: str, price: float):
    res = requests.post(f"{product}/{product_id}/price", json=price)
    if res.status_code == 202:
        print("Updated")
    else:
        print("Update Failed")
    return

def product_update(product_id: str, price: float, amount: int):
    update_price(product_id, price)
    increase_stock(product_id, amount)


def main():
    while(True):
        print("User:")

        u = ""

        if(len(users) <= 10):
            u_id = random_uuid()
            u_name = fake.name()

            u = register_user(u_id, u_name)
        else:
            u = random.choice(users)



        print()

        sleep(5)

        print("Product:")

        if (len(products) <= 6):
            p_id = random_uuid()
            p_name = f"{fake.color_name()} {fake.word().capitalize()}"
            p_price = fake.pyfloat(left_digits=2, right_digits=2, positive=True, min_value=5.0, max_value=99.99)
            p_stock = fake.random_int(min=10, max=100)

            create_product(p_id, p_name, "", p_price, p_stock)

        print()

        sleep(5)

        for _ in range(0, random.randint(2, 5)):
            p = random.choice(products)
            add_cart_item(u, p, random.randint(5, 20))


        sleep(5)

        print("Order:")

        checkout_cart(u)

        print()

        sleep(5)

        print("Product Update:")

        p = random.choice(products)

        print(p)

        product_update(p, random_price(), random.randint(20, 100))

        print()

        sleep(5)


if __name__ == "__main__":
    main()
