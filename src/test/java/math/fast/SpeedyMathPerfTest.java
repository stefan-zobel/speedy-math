/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package math.fast;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 * Performance tests for SpeedyMath.
 */
public class SpeedyMathPerfTest {
    private static final int RUNS = Integer.parseInt(System.getProperty("testRuns", "100000000"));
    private static final double F1 = 1d / RUNS;

    // Header format
    private static final String FMT_HDR = "%-5s %13s %13s %13s Runs=%d Java %s (%s) %s (%s)";
    // Detail format
    private static final String FMT_DTL = "%-5s %6d %6.1f %6d %6.4f %6d %6.4f";

    @BeforeClass
    public static void header() {
        System.out.println(String.format(FMT_HDR, "Name", "StrictMath", "SpeedyMath", "Math", RUNS,
                System.getProperty("java.version"), System.getProperty("java.runtime.version", "?"),
                System.getProperty("java.vm.name"), System.getProperty("java.vm.version")));
    }

    private static void report(String name, long strictMathTime, long speedyMathTime, long mathTime) {
        long unitTime = strictMathTime;
        System.out.println(String.format(FMT_DTL, name, strictMathTime / RUNS, (double) strictMathTime / unitTime,
                speedyMathTime / RUNS, (double) speedyMathTime / unitTime, mathTime / RUNS,
                (double) mathTime / unitTime));
    }

    @Test
    public void testAsin() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.asin(0.999 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.asin(0.999 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.asin(0.999 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;

        report("asin", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testCos() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.cos(100 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.cos(100 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.cos(100 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;

        report("cos", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testAcos() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.acos(0.999 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.acos(0.999 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.acos(0.999 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;
        report("acos", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testAtan() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.atan(100 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.atan(100 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.atan(100 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;

        report("atan", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testAtan2() {
        double x = 0;
        long time = System.nanoTime();
        int max = (int) Math.floor(Math.sqrt(RUNS));
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                x += StrictMath.atan2((i - max / 2) * (100.0 / max), (j - max / 2) * (100.0 / max));
            }
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                x += SpeedyMath.atan2((i - max / 2) * (100.0 / max), (j - max / 2) * (100.0 / max));
            }
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                x += Math.atan2((i - max / 2) * (100.0 / max), (j - max / 2) * (100.0 / max));
            }
        }
        long mathTime = System.nanoTime() - time;

        report("atan2", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testHypot() {
        double x = 0;
        long time = System.nanoTime();
        int max = (int) Math.floor(Math.sqrt(RUNS));
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                x += StrictMath.atan2((i - max / 2) * (100.0 / max), (j - max / 2) * (100.0 / max));
            }
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                x += SpeedyMath.atan2((i - max / 2) * (100.0 / max), (j - max / 2) * (100.0 / max));
            }
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                x += Math.atan2((i - max / 2) * (100.0 / max), (j - max / 2) * (100.0 / max));
            }
        }
        long mathTime = System.nanoTime() - time;

        report("hypot", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testCosh() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.cosh(100 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.cosh(100 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.cosh(100 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;

        report("cosh", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testSinh() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.sinh(100 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.sinh(100 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.sinh(100 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;

        report("sinh", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testTanh() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.tanh(100 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.tanh(100 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.tanh(100 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;

        report("tanh", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }

    @Test
    public void testExpm1() {
        double x = 0;
        long time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += StrictMath.expm1(100 * (i - RUNS / 2) * F1);
        }
        long strictTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += SpeedyMath.expm1(100 * (i - RUNS / 2) * F1);
        }
        long fastTime = System.nanoTime() - time;

        x = 0;
        time = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            x += Math.expm1(100 * (i - RUNS / 2) * F1);
        }
        long mathTime = System.nanoTime() - time;
        report("expm1", strictTime, fastTime, mathTime);
        Assert.assertTrue(!Double.isNaN(x));
    }
}
