package at.fhv.common.infrastructure.persistence;

import java.util.Currency;

import at.fhv.common.domain.model.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
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
