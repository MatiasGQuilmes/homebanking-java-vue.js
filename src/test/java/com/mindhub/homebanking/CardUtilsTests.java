//package com.mindhub.homebanking;
//
//
//import com.mindhub.homebanking.utils.CardUtils;
//import org.hamcrest.Matcher;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Collection;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.emptyOrNullString;
//
//
//import static org.hamcrest.Matchers.*;
//
//
//@SpringBootTest
//public class CardUtilsTests {
//
//    @Test
//    public void cardNumberIsCreated(){
//
//        String cardNumber = CardUtils.getCardNumber();
//
//        assertThat(cardNumber,is(not(emptyOrNullString())));
//
//    }
//
//    @Test
//    public void cardCvvIsCreated(){
//
//        int cardCvv = CardUtils.getCvv();
//
//        assertThat(cardCvv,is(not(0)));
//
//    }
//
//}
