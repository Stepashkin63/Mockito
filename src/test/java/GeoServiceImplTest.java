import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.geo.GeoServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class GeoServiceImplTest {
    static GeoServiceImpl sut;

    @BeforeAll
    public static void startsAll() {
        sut = new GeoServiceImpl();

        System.out.println("Testing GeoServiceImpl starts");
    }

    @AfterEach
    public void finished() {
        System.out.println(" - complete");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Testing GeoServiceImpl completed");
    }

    @Test
    @DisplayName("Test byIp() for localhost")
    public void byIpLocalhostTest(TestInfo byIpLocalhostTestInfo) {
        Assertions.assertNull(sut.byIp("127.0.0.1").getCountry(), byIpLocalhostTestInfo.getDisplayName() + " not complete");
        System.out.print(byIpLocalhostTestInfo.getDisplayName());
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for rus IP")
    @ValueSource(strings = {"172.0.32.11"})
    public void byRussiaIpTest(String ip) {
        Assertions.assertEquals(RUSSIA, sut.byIp(ip).getCountry());
        System.out.print("Test byIp (" + ip + ")");
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for USA IP")
    @ValueSource(strings = {"96.44.183.149"})
    public void byUsaIpTest(String ip) {
        Assertions.assertEquals(USA, sut.byIp(ip).getCountry());
        System.out.print("Test byIp (" + ip + ")");
    }

    @Test
    @DisplayName("Test byCoordinates")
    public void byCoordinatesTest(TestInfo byCoordinatesTestInfo) {
        Assertions.assertThrows(RuntimeException.class, () -> sut.byCoordinates(55.45, 37.36),
                byCoordinatesTestInfo.getDisplayName() + " not complete");
        System.out.print(byCoordinatesTestInfo.getDisplayName());
    }
}