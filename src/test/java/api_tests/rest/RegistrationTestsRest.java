package api_tests.rest;

import api.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username("john_smith"+i+"@gmail.com")
                .password("Password12345!")
                .firstName("John")
                .lastName("Smith")
                .build();
        Assert.assertEquals(registrationLogin(registrationBodyDto, REGISTRATION_URL).getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_EmptyPassword(){
        int i = new Random().nextInt(1000)+1000;
        RegistrationBodyDto registrationBodyDto = RegistrationBodyDto.builder()
                .username("john_smith"+i+"@gmail.com")
                .password("")
                .firstName("John")
                .lastName("Smith")
                .build();
        Assert.assertEquals(registrationLogin(registrationBodyDto,REGISTRATION_URL).getBody()
                .as(ErrorMessageDtoString.class)
                .getError(), "Bad Request");
        Assert.assertEquals(registrationLogin(registrationBodyDto,REGISTRATION_URL).getBody()
                .as(ErrorMessageDtoString.class)
                .getStatus(), 400);
    }
}
