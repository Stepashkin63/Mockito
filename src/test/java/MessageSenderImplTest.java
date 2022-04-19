import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class MessageSenderImplTest {

    static MessageSender messageSender;
    static LocalizationService localizationService;
    static GeoService geoService;

    @BeforeAll
    public static void startsAll() {
        geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");

        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @AfterEach
    public void finished() {
        System.out.println(" - complete");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Testing MessageSenderImpl completed");
    }

    @Test
    @DisplayName("Test sendMessage for RUSSIA ip")
    public void sendMessageRussiaIpTest(TestInfo sendMessageRussiaIpTestInfo) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers),
                sendMessageRussiaIpTestInfo.getDisplayName() + " not complete");
        System.out.print(sendMessageRussiaIpTestInfo.getDisplayName());
    }

    @Test
    @DisplayName("Test sendMessage for USA ip")
    public void sendMessageUsaIpTest(TestInfo sendMessageUsaIpTestInfo) {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Assertions.assertEquals("Welcome", messageSender.send(headers),
                sendMessageUsaIpTestInfo.getDisplayName() + " not complete");
        System.out.print(sendMessageUsaIpTestInfo.getDisplayName());
    }
}