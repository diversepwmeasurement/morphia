package dev.morphia.aggregation.codecs.stages;

import dev.morphia.MorphiaDatastore;
import dev.morphia.aggregation.stages.PlanCacheStats;

import org.bson.BsonWriter;
import org.bson.codecs.EncoderContext;

import static dev.morphia.mapping.codec.CodecHelper.document;

public class PlanCacheStatsCodec extends StageCodec<PlanCacheStats> {
    public PlanCacheStatsCodec(MorphiaDatastore datastore) {
        super(datastore);
    }

    @Override
    public Class<PlanCacheStats> getEncoderClass() {
        return PlanCacheStats.class;
    }

    @Override
    protected void encodeStage(BsonWriter writer, PlanCacheStats value, EncoderContext encoderContext) {
        document(writer, () -> {
        });
    }
}
