package desafio.varejo.cellstoreapi.factory;

import desafio.varejo.cellstoreapi.model.Color;
import desafio.varejo.cellstoreapi.model.Phone;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestPhoneFactory {

    public static Phone getTestPhone1() {
        return new Phone(
                "hdus8yd8",
                "HTXXZ9",
                "Moto",
                new BigDecimal("1300.00"),
                "http://www.google.com/any1.png",
                LocalDate.parse("2019-01-02"),
                LocalDate.parse("2019-12-31"),
                Color.BLACK
        );
    }

    public static Phone getTestPhone2() {
        return new Phone(
                "appleyd8",
                "IphoneZ",
                "Apple",
                new BigDecimal("13000.00"),
                "http://www.google.com/any2.png",
                LocalDate.parse("2019-10-02"),
                LocalDate.parse("2019-12-31"),
                Color.BLACK
        );
    }

    public static Phone getTestPhone3() {
        return new Phone(
                "hxcdfdfd",
                "HZZYY2000",
                "Moto",
                new BigDecimal("2300.00"),
                "http://www.google.com/any3.png",
                LocalDate.parse("2019-01-01"),
                LocalDate.parse("2019-10-30"),
                Color.GOLD
        );
    }

    public static List<Phone> getTestPhones() {
        return List.of(getTestPhone1(), getTestPhone2(), getTestPhone3());
    }
}
