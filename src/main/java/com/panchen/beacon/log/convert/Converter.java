package com.panchen.beacon.log.convert;

import com.panchen.beacon.log.base.LogMetadata;

public interface Converter {

    Object convert(LogMetadata logMetadata);

}
