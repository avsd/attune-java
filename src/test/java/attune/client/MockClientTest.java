package attune.client;

import attune.client.model.RankingParams;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

/**
 * MockClient Tester.
 *
 * @author <sudnya>
 * @since <pre>June 2, 2015</pre>
 * @version 1.0
 */

public class MockClientTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: get auth token
     * @throws Exception
     */
    @Test
    public void testGetAuthTokenFromMock() throws Exception {
        MockClient mockClient  = new MockClient();
        assertNotNull(mockClient.getAuthToken());
    }

    /**
     * Method: test a anonymous get request
     * @throws Exception
     */
    @Test
    public void testCreateAnonymousFromMock() throws Exception {
        MockClient mockClient = new MockClient();
        assertNotNull(mockClient.createAnonymous(""));
    }


    /**
     * Method: test a binding call request
     * @throws Exception
     */
    @Test
    public void testBindFromMock() throws Exception {
        MockClient mockClient = new MockClient();
        mockClient.bind("", "", "");
    }


    /**
     * Method: get customer id bound to an anonymous id
     * @throws Exception
     */
    @Test
    public void testBoundCustomerFromMock() throws Exception {
        MockClient mockClient = new MockClient();
        assertNotNull(mockClient.getBoundCustomer("", ""));
    }


    /**
     * Method: verify that the rankings returned on a get call happened correctly and the size of the list matches the list supplied in the params
     * @throws Exception
     */
    @Test
    public void testGetRankingsFromMock() throws Exception {
        MockClient mockClient = new MockClient();
        assertNotNull(mockClient.getRankings(new RankingParams(), ""));
    }
}
