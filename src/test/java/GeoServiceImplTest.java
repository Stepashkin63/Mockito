import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {
    static GeoService geoService;

    @BeforeAll
    public static void startsAll() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(LOCALHOST))
                .thenReturn(new Location(null, null, null, 0));
        Mockito.when(geoService.byIp(MOSCOW_IP))
                .thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp(NEW_YORK_IP))
                .thenReturn(new Location("New York", USA, "10th Avenue", 32));
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", USA, null, 0));
        Mockito.when(geoService.byCoordinates(Mockito.anyDouble(), Mockito.anyDouble()))
                .thenThrow(new RuntimeException("Not implemented"));
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
        Assertions.assertNull(geoService.byIp("127.0.0.1").getCountry(), byIpLocalhostTestInfo.getDisplayName() + " not complete");
        System.out.print(byIpLocalhostTestInfo.getDisplayName());
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for rus IP")
    @ValueSource(strings = {"172.0.32.11"})
    public void byRussiaIpTest(String ip) {
        Assertions.assertEquals(RUSSIA, geoService.byIp(ip).getCountry());
        System.out.print("Test byIp (" + ip + ")");
    }

    @ParameterizedTest
    @DisplayName("Test byIp() for USA IP")
    @ValueSource(strings = {"96.44.183.149"})
    public void byUsaIpTest(String ip) {
        Assertions.assertEquals(USA, geoService.byIp(ip).getCountry());
        System.out.print("Test byIp (" + ip + ")");
    }

    @Test
    @DisplayName("Test byCoordinates")
    public void byCoordinatesTest(TestInfo byCoordinatesTestInfo) {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(55.45, 37.36),
                byCoordinatesTestInfo.getDisplayName() + " not complete");
        System.out.print(byCoordinatesTestInfo.getDisplayName());
    }
}