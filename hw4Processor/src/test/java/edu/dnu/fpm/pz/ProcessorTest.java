package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.assets.Consumer;
import edu.dnu.fpm.pz.assets.Producer;
import edu.dnu.fpm.pz.processor.Processor;
import org.junit.Before;
import org.junit.Rule;
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
    public void testProcessorWithProducerPredefinedBehavior() {
        // Given
        String predefinedString = "Magic value";
        Mockito.when(producer.produce()).thenReturn(predefinedString);

        // When
        processor.process();

        // Then
        Mockito.verify(producer, Mockito.times(1)).produce();
        Mockito.verifyNoMoreInteractions(producer);
    }

    @Test
    public void testProcessorWithConsumerPredefinedBehavior () {
        // Given
        String producedString = "Magic value";
        Mockito.when(producer.produce()).thenReturn(producedString);

        // When
        processor.process();

        //Then
        Mockito.verify(consumer, Mockito.times(1)).consume(valueCaptor.capture());
        String actualValue = valueCaptor.getValue();
        assertEquals(producedString, actualValue);
    }

    @Test
    public void testProcessWithException() {
        // Given
        thrown.expect(IllegalStateException.class);

        // When
        processor.process();
    }
}
