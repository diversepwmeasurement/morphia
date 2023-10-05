package dev.morphia.mapping.codec;

import java.util.HashMap;
import java.util.Map;

import dev.morphia.MorphiaDatastore;
import dev.morphia.mapping.codec.filters.BaseFilterCodec;
import dev.morphia.mapping.codec.filters.FilterCodec;
import dev.morphia.query.filters.Filter;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class MorphiaFilterCodecProvider implements CodecProvider {
    protected final MorphiaDatastore datastore;
    private final Map<Class<?>, BaseFilterCodec<?>> codecs = new HashMap<>();

    public MorphiaFilterCodecProvider(MorphiaDatastore datastore) {
        this.datastore = datastore;
        addCodec(new BoxCodec(datastore));
        addCodec(new CenterFilterCodec(datastore));
        addCodec(new ElemMatchFilterCodec(datastore));
        addCodec(new EqFilterCodec(datastore));
        addCodec(new ExistsFilterCodec(datastore));
        addCodec(new ExprFilterCodec(datastore));
        addCodec(new FilterCodec(datastore));
        addCodec(new GeoIntersectsFilterCodec(datastore));
        addCodec(new JsonSchemaFilterCodec(datastore));
        addCodec(new LogicalFilterCodec(datastore));
        addCodec(new NearFilterCodec(datastore));
        addCodec(new PolygonFilterCodec(datastore));
        addCodec(new RegexFilterCodec(datastore));
        addCodec(new SampleRateFilterCodec(datastore));
        addCodec(new TextSearchFilterCodec(datastore));
        addCodec(new WhereFilterCodec(datastore));
    }

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        Codec<T> codec = (Codec<T>) codecs.get(clazz);

        if (codec == null && Filter.class.isAssignableFrom(clazz)) {
            throw new UnsupportedOperationException(clazz.getName() + " needs a codec");
        }
        return codec;
    }

    private void addCodec(BaseFilterCodec<?> codec) {
        codecs.put(codec.getEncoderClass(), codec);
    }

}