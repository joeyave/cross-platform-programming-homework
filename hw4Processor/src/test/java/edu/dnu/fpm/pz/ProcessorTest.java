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
import static org.mockito.Mockito.times;

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
    public void testConsume() {
        // Given.
        String predefinedString = "Something";

        // When.
        consumer.consume(predefinedString);

        // Then.
        Mockito.verify(consumer, Mockito.times(1)).consume(valueCaptor.capture());
        String actualString = valueCaptor.getValue();
        assertEquals(predefinedString, actualString);
    }

    @Test
    public void testProduce() {
        // Given.
        String predefinedString = "Magic value";
        Mockito.when(producer.produce()).thenReturn(predefinedString);

        // When.
        String result = producer.produce();

        // Then.
        Assert.assertNotNull(result);
        Assert.assertEquals(predefinedString, result);
        Mockito.verify(producer, times(1)).produce();
        Mockito.verifyNoMoreInteractions(producer);
    }

    @Test
    public void testProcessWithException() {
        // Given.
        thrown.expect(IllegalStateException.class);

        // When.
        processor.process();
    }
}
