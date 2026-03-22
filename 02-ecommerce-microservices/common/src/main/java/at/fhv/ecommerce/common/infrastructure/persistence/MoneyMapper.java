package at.fhv.ecommerce.common.infrastructure.persistence;

import java.util.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import at.fhv.ecommerce.common.domain.Money;

@Mapper()
public interface MoneyMapper {
    @Mapping(target = "value", source = "amount")
    @Mapping(target = "add", ignore = true)
    @Mapping(target = "subtract", ignore = true)
    Money toMoney(MoneyEmbed embed);

    @Mapping(target = "amount", source = "value")
    MoneyEmbed toEmbed(Money money);

    default String map(Currency currency) {
        return currency != null ? currency.getCurrencyCode() : null;
    }

    default Currency map(String currency) {
        return currency != null ? Currency.getInstance(currency) : null;
    }
}
