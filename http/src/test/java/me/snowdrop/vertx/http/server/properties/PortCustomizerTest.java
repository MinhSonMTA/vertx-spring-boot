package me.snowdrop.vertx.http.server.properties;

import io.vertx.core.http.HttpServerOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PortCustomizerTest {

    @Mock
    private AbstractConfigurableWebServerFactory mockFactory;

    @Mock
    private HttpServerOptions mockHttpServerOptions;

    @Test
    public void shouldSetValidPort() {
        given(mockFactory.getPort()).willReturn(1);

        PortCustomizer customizer = new PortCustomizer(mockFactory);
        customizer.apply(mockHttpServerOptions);

        verify(mockHttpServerOptions).setPort(1);
    }

    @Test
    public void shouldIgnoreInvalidPort() {
        given(mockFactory.getPort()).willReturn(-1);

        PortCustomizer customizer = new PortCustomizer(mockFactory);
        customizer.apply(mockHttpServerOptions);

        verify(mockHttpServerOptions, times(0)).setPort(anyInt());
    }

}
