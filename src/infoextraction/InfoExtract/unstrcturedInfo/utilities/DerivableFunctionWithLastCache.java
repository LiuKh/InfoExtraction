/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.utilities;

import infoextraction.InfoExtract.unstrcturedInfo.function.DerivableFunction;
import org.apache.log4j.Logger;

/**
 *
 * @author Kaihua Liu
 */
public class DerivableFunctionWithLastCache extends DerivableFunction {

    public DerivableFunctionWithLastCache(DerivableFunction realFunction) {
        super();
        this.realFunction = realFunction;
    }

    public double value(double[] point) {
        double ret = 0.0;
        DoubleArrayWrapper wrappedPoint = new DoubleArrayWrapper(point);
        Double fromCache = valueCache.get(wrappedPoint);
        if (null == fromCache) {
            double calculatedValue = realFunction.value(point);
            valueCache.put(wrappedPoint, calculatedValue);
            ret = calculatedValue;
        } else {
            logger.debug("Returning value from cache");
            ret = fromCache;
        }
        return ret;
    }

    public double[] gradient(double[] point) {
        double[] ret = null;
        DoubleArrayWrapper wrappedPoint = new DoubleArrayWrapper(point);
        double[] fromCache = gradientCache.get(wrappedPoint);
        if (null == fromCache) {
            double[] calculatedGradient = realFunction.gradient(point);
            gradientCache.put(wrappedPoint, calculatedGradient);
            ret = calculatedGradient;
        } else {
            logger.debug("Returning gradient from cache");
            ret = fromCache;
        }
        return ret;
    }

    public int size() {
        return realFunction.size();
    }

    private final LastCache<DoubleArrayWrapper, Double> valueCache = new LastCache<DoubleArrayWrapper, Double>();
    private final LastCache<DoubleArrayWrapper, double[]> gradientCache = new LastCache<DoubleArrayWrapper, double[]>();

    private final DerivableFunction realFunction;

    private static final Logger logger = Logger.getLogger(DerivableFunctionWithLastCache.class);
}
