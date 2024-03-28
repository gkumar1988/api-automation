package tests;
import static com.google.common.truth.Truth.assertThat;

import api.CurrencyExchangeTestBase;
import org.testng.annotations.Test;

public class TestMethod extends CurrencyExchangeTestBase {

    @Test
    public void testListQuotes() {
        assertThat(listQuotes().getStatusCode()).isEqualTo(200);
    }

    @Test
    public void test123() {
        assertThat(1).isEqualTo(1);
    }
}
