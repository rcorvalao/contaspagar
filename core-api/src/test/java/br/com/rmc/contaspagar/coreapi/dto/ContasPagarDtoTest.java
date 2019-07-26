package br.com.rmc.contaspagar.coreapi.dto;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ContasPagarDtoTest {

    @Test
    public void validaBeanComSucesso() throws Exception {

        assertNotNull(new ContasPagarDto());

        assertThat(ContasPagarDto.class, allOf(
                hasValidBeanConstructor(), hasValidGettersAndSetters()
                ));
    }

}
