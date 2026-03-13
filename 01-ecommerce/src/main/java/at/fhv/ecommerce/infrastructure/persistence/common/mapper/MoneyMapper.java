package at.fhv.ecommerce.infrastructure.persistence.common.mapper;

import java.util.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import at.fhv.ecommerce.domain.common.Money;
import at.fhv.ecommerce.infrastructure.persistence.common.entity.MoneyEmbed;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
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
