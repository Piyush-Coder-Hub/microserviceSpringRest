package piyush.springframework.msscbeerclient.web.config;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Apache HTTP Client
 * @author Piyush
 *
 */
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
	
	private final Integer maxTotalConnnecions;
	private final Integer defaultMaxTotalConnections;
	private final Integer connnectionRequestTimeout;
	private final Integer socketTimeout;
	
    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotalconnnecions}")Integer maxTotalConnnecions,
    									  @Value("${sfg.defaultmaxtotalconnnecions}")Integer defaultMaxTotalConnections,
    									  @Value("${sfg.connectionrequesttimeout}")Integer connnectionRequestTimeout,
    									  @Value("${sfg.sockettimeout}")Integer socketTimeout) {
		this.maxTotalConnnecions = maxTotalConnnecions;
		this.defaultMaxTotalConnections = defaultMaxTotalConnections;
		this.connnectionRequestTimeout = connnectionRequestTimeout;
		this.socketTimeout = socketTimeout;
	}

	public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotalConnnecions);
        connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnections);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connnectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}