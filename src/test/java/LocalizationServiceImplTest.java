import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {
    static LocalizationService localizationService;

    @BeforeAll
    public static void startsAll() {
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать!");
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome!");
        System.out.println("Testing LocalizationServiceImpl starts");
    }

    @AfterEach
    public void finished() {
        System.out.println(" - complete");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Testing LocalizationServiceImpl completed");
    }

    @Test
    @DisplayName("Test locale for RUSSIA")
    public void localeRussiaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(RUSSIA), "Добро пожаловать!",
                localeTestInfo.getDisplayName() + " not complete");
        System.out.print(localeTestInfo.getDisplayName());
    }

    @Test
    @DisplayName("Test locale for USA")
    public void localeUsaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals(localizationService.locale(USA), "Welcome!",
                localeTestInfo.getDisplayName() + " not complete");
        System.out.print(localeTestInfo.getDisplayName());
    }
}