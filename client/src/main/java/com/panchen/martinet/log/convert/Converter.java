package com.panchen.collector.log.convert;

import com.panchen.collector.log.base.LogMetadata;

public interface Converter {

    Object convert(LogMetadata logMetadata);

}
