package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    @DisplayName("MessageCodesResolver 테스트")
    void messageCodesResolverObject(){

        //give
        String[] messagesCodes = codesResolver.resolveMessageCodes("required", "item");


        //when
        for( String messageCode: messagesCodes){
            System.out.println("messageCode = "+ messageCode);
        }

        //then
        assertThat(messagesCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required","item","itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode= "+ messageCode);
        }
        // compile result:
        // messageCode= required.item.itemName -> 제일 복잡한 단계
        // messageCode= required.itemName
        // messageCode= required.java.lang.String -> 문자로 받아야 한다는 의미
        // messageCode= required
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );

    }
}
