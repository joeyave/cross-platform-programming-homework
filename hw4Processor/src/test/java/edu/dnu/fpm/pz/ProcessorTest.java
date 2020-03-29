package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.assets.Consumer;
import edu.dnu.fpm.pz.assets.Producer;
import edu.dnu.fpm.pz.processor.Processor;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import static org.junit.Assert.assertEquals;

public class ProcessorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    Producer producer;

    @Mock
    Consumer consumer;

    @Captor
    private ArgumentCaptor<String> valueCaptor;

    @InjectMocks
    Processor processor = new Processor();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProduce() {
        // Given.
        String producedString = "Magic value";
        Mockito.when(producer.produce()).thenReturn(producedString);

        // When.
        String result = producer.produce();

        // Then.
        Assert.assertNotEquals(producedString, result);
        Assert.assertEquals(producedString, result);
        Mockito.verify(producer).produce();
    }

    @Test
    public void testConsume() {
        // Given.
        String producedString = "Magic value";

        // When.
        consumer.consume(producedString);

        // Then.
        Mockito.verify(consumer).consume(valueCaptor.capture());
        String actual = valueCaptor.getValue();
        assertEquals(producedString, actual);
    }

    @Test
    public void testProcessWithException() {
        // Given.
        thrown.expect(IllegalStateException.class);

        // When.
        processor.process();

        // Then.
    }

    @Test
    public void testProcessWithoutException() {
        // Given.
        String producedString = "Magic value";
        Mockito.when(producer.produce()).thenReturn(producedString);

        // When.
        processor.process();

        // Then.
        Mockito.verify(consumer).consume(valueCaptor.capture());
        String actual = valueCaptor.getValue();
        assertEquals(producedString, actual);
    }
}
