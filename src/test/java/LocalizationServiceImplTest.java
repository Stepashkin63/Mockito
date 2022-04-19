import org.junit.jupiter.api.*;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class LocalizationServiceImplTest {
    static LocalizationServiceImpl sut;

    @BeforeAll
    public static void startsAll() {
        sut = new LocalizationServiceImpl();
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
        Assertions.assertEquals("Добро пожаловать", sut.locale(RUSSIA),
                localeTestInfo.getDisplayName() + " not complete");
        System.out.print(localeTestInfo.getDisplayName());
    }

    @Test
    @DisplayName("Test locale for USA")
    public void localeUsaTest(TestInfo localeTestInfo) {
        Assertions.assertEquals("Welcome", sut.locale(USA),
                localeTestInfo.getDisplayName() + " not complete");
        System.out.print(localeTestInfo.getDisplayName());
    }
}