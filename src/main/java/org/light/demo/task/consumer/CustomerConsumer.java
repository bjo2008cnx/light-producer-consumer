package org.light.demo.task.consumer;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.light.demo.task.common.event.CommonEvent;
import org.light.demo.task.common.event.CommonEventConsumer;
import org.light.demo.task.common.event.CommonEventStarter;
import org.light.demo.task.domain.CustomerData;

/**
 * CustomerConsumer
 *
 * @author Michael.Wang
 * @date 2017/6/19
 */
@Slf4j
public class CustomerConsumer extends CommonEventConsumer {
    @Override
    public void onEvent(CommonEvent event, long sequence, boolean endOfBatch) {
        log.debug("consume log[start]");
        RingBuffer<CommonEvent> ringBuffer = CommonEventStarter.getDisrutor().getRingBuffer();
        CommonEvent<CustomerData> CommonEvent = ringBuffer.get(sequence);
        CustomerData entity = CommonEvent.getValue();
        DataConsumer.consume(entity);
        log.debug("consume log[end]");
    }
}