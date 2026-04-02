package com.memoeslink.common;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RandomizerTest {

    @Nested
    class GetSeed {

        @Test
        void withDefaultConstructor_returnsNull() {
            assertNull(new Randomizer().getSeed());
        }

        @Test
        void withSeededConstructor_returnsNonNull() {
            assertNotNull(new Randomizer(0L).getSeed());
        }
    }

    @Nested
    class BindSeed {

        @Test
        void withNull_clearsSeed() {
            Randomizer r = new Randomizer(0L);
            r.bindSeed(null);
            assertNull(r.getSeed());
        }

        @Test
        void withLong_setsSeed() {
            Randomizer r = new Randomizer();
            r.bindSeed(0L);
            assertNotNull(r.getSeed());
        }
    }

    @Nested
    class UnbindSeed {

        @Test
        void withSeededRandomizer_clearsSeed() {
            Randomizer r = new Randomizer(0L);
            r.unbindSeed();
            assertNull(r.getSeed());
        }
    }

    @Nested
    class GetBoolean {

        @Test
        void withNoSeed_returnsBothValuesEventually() {
            Randomizer r = new Randomizer();
            List<Boolean> values = IntStream.range(0, 100).mapToObj(i -> r.getBoolean()).toList();
            assertTrue(values.contains(true));
            assertTrue(values.contains(false));
        }

        @Test
        void withSameSeed_returnsIdenticalSequence() {
            Randomizer r1 = new Randomizer(42L);
            Randomizer r2 = new Randomizer(42L);
            List<Boolean> seq1 = IntStream.range(0, 20).mapToObj(i -> r1.getBoolean()).collect(Collectors.toList());
            List<Boolean> seq2 = IntStream.range(0, 20).mapToObj(i -> r2.getBoolean()).collect(Collectors.toList());
            assertEquals(seq1, seq2);
        }
    }

    @Nested
    class GetInt {

        @Test
        void withNoArgs_doesNotThrow() {
            assertDoesNotThrow(() -> new Randomizer().getInt());
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getInt(), new Randomizer(42L).getInt());
        }

        @Nested
        class WithBound {

            @Test
            void withPositiveBound_returnsValueInRange() {
                int value = new Randomizer().getInt(100);
                assertTrue(0 <= value && value < 100);
            }

            @ParameterizedTest
            @ValueSource(ints = {0, -1, -100})
            void withNonPositiveBound_returnsZero(int bound) {
                assertEquals(0, new Randomizer().getInt(bound));
            }
        }

        @Nested
        class WithOrigin {

            @Test
            void withValidOriginAndBound_returnsValueInRange() {
                int value = new Randomizer().getInt(-15, -10);
                assertTrue(-15 <= value && value < -10);
            }

            @Test
            void withBoundLesserThanOrigin_returnsZero() {
                assertEquals(0, new Randomizer().getInt(-15, -25));
            }
        }
    }

    @Nested
    class GetIntInRange {

        @Test
        void withNegativeAndPositiveLimits_returnsValueInRange() {
            int value = new Randomizer().getIntInRange(-5, 5);
            assertTrue(-5 <= value && value <= 5);
        }

        @Test
        void withPositiveLimits_returnsValueInRange() {
            int value = new Randomizer().getIntInRange(20, 30);
            assertTrue(20 <= value && value <= 30);
        }

        @Test
        void withNegativeLimits_returnsValueInRange() {
            int value = new Randomizer().getIntInRange(-10, -5);
            assertTrue(-10 <= value && value <= -5);
        }

        @Test
        void withEqualLimits_returnsThatValue() {
            assertEquals(-10, new Randomizer().getIntInRange(-10, -10));
        }

        @Test
        void withMaxLesserThanMin_returnsZero() {
            assertEquals(0, new Randomizer().getIntInRange(-10, -15));
        }
    }

    @Nested
    class GetInts {

        @Test
        void withCount_returnsCorrectSize() {
            assertEquals(10, new Randomizer().getInts(10).size());
        }

        @Test
        void withCount_containsNoNulls() {
            assertTrue(new Randomizer().getInts(10).stream().noneMatch(Objects::isNull));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getInts(count).isEmpty());
        }

        @Test
        void withCountAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getInts(50, 10).stream().allMatch(v -> 0 <= v && v < 10));
        }

        @Test
        void withCountOriginAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getInts(50, -100, 100).stream().allMatch(v -> -100 <= v && v < 100));
        }
    }

    @Nested
    class GetIntsInRange {

        @Test
        void withCountAndLimits_returnsCorrectSize() {
            assertEquals(50, new Randomizer().getIntsInRange(50, -5, 5).size());
        }

        @Test
        void withCountAndLimits_returnsAllInRange() {
            assertTrue(new Randomizer().getIntsInRange(50, -5, 5).stream().allMatch(v -> -5 <= v && v <= 5));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getIntsInRange(count, -5, 5).isEmpty());
        }
    }

    @Nested
    class GetLong {

        @Test
        void withNoArgs_doesNotThrow() {
            assertDoesNotThrow(() -> new Randomizer().getLong());
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getLong(), new Randomizer(42L).getLong());
        }

        @Nested
        class WithBound {

            @Test
            void withPositiveBound_returnsValueInRange() {
                long value = new Randomizer().getLong(100L);
                assertTrue(0L <= value && value < 100L);
            }

            @ParameterizedTest
            @ValueSource(longs = {0L, -1L, -100L})
            void withNonPositiveBound_returnsZero(long bound) {
                assertEquals(0L, new Randomizer().getLong(bound));
            }
        }

        @Nested
        class WithOrigin {

            @Test
            void withValidOriginAndBound_returnsValueInRange() {
                long value = new Randomizer().getLong(-15L, -10L);
                assertTrue(-15L <= value && value < -10L);
            }

            @Test
            void withBoundLesserThanOrigin_returnsZero() {
                assertEquals(0L, new Randomizer().getLong(-15L, -25L));
            }
        }
    }

    @Nested
    class GetLongInRange {

        @Test
        void withNegativeAndPositiveLimits_returnsValueInRange() {
            long value = new Randomizer().getLongInRange(-5L, 5L);
            assertTrue(-5L <= value && value <= 5L);
        }

        @Test
        void withPositiveLimits_returnsValueInRange() {
            long value = new Randomizer().getLongInRange(20L, 30L);
            assertTrue(20L <= value && value <= 30L);
        }

        @Test
        void withNegativeLimits_returnsValueInRange() {
            long value = new Randomizer().getLongInRange(-10L, -5L);
            assertTrue(-10L <= value && value <= -5L);
        }

        @Test
        void withEqualLimits_returnsThatValue() {
            assertEquals(-10L, new Randomizer().getLongInRange(-10L, -10L));
        }

        @Test
        void withMaxLesserThanMin_returnsZero() {
            assertEquals(0L, new Randomizer().getLongInRange(-10L, -15L));
        }
    }

    @Nested
    class GetLongs {

        @Test
        void withCount_returnsCorrectSize() {
            assertEquals(10, new Randomizer().getLongs(10).size());
        }

        @Test
        void withCount_containsNoNulls() {
            assertTrue(new Randomizer().getLongs(10).stream().noneMatch(Objects::isNull));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getLongs(count).isEmpty());
        }

        @Test
        void withCountAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getLongs(50, 10).stream().allMatch(v -> 0L <= v && v < 10L));
        }

        @Test
        void withCountOriginAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getLongs(50, -100L, 100L).stream().allMatch(v -> -100L <= v && v < 100L));
        }
    }

    @Nested
    class GetLongsInRange {

        @Test
        void withCountAndLimits_returnsCorrectSize() {
            assertEquals(50, new Randomizer().getLongsInRange(50, -5L, 5L).size());
        }

        @Test
        void withCountAndLimits_returnsAllInRange() {
            assertTrue(new Randomizer().getLongsInRange(50, -5L, 5L).stream().allMatch(v -> -5L <= v && v <= 5L));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getLongsInRange(count, -5L, 5L).isEmpty());
        }
    }

    @Nested
    class GetFloat {

        @Test
        void withNoArgs_returnsValueInUnitRange() {
            float value = new Randomizer().getFloat();
            assertTrue(0.0F <= value && value <= 1.0F);
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getFloat(), new Randomizer(42L).getFloat());
        }

        @Nested
        class WithBound {

            @Test
            void withPositiveBound_returnsValueInRange() {
                float value = new Randomizer().getFloat(100.0F);
                assertTrue(0.0F <= value && value < 100.0F);
            }

            @ParameterizedTest
            @ValueSource(floats = {0.0F, -0.001F, -100.0F})
            void withNonPositiveBound_returnsZero(float bound) {
                assertEquals(0.0F, new Randomizer().getFloat(bound));
            }
        }

        @Nested
        class WithOrigin {

            @Test
            void withValidOriginAndBound_returnsValueInRange() {
                float value = new Randomizer().getFloat(-15.0F, -10.0F);
                assertTrue(-15.0F <= value && value < -10.0F);
            }

            @Test
            void withBoundLesserThanOrigin_returnsZero() {
                assertEquals(0.0F, new Randomizer().getFloat(-15.0F, -25.0F));
            }
        }
    }

    @Nested
    class GetFloatInRange {

        @Test
        void withNegativeAndPositiveLimits_returnsValueInRange() {
            float value = new Randomizer().getFloatInRange(-5.0F, 5.0F);
            assertTrue(-5.0F <= value && value <= 5.0F);
        }

        @Test
        void withPositiveLimits_returnsValueInRange() {
            float value = new Randomizer().getFloatInRange(20.0F, 30.0F);
            assertTrue(20.0F <= value && value <= 30.0F);
        }

        @Test
        void withNegativeLimits_returnsValueInRange() {
            float value = new Randomizer().getFloatInRange(-10.0F, -5.0F);
            assertTrue(-10.0F <= value && value <= -5.0F);
        }

        @Test
        void withEqualLimits_returnsThatValue() {
            assertEquals(-10.0F, new Randomizer().getFloatInRange(-10.0F, -10.0F));
        }

        @Test
        void withMaxLesserThanMin_returnsZero() {
            assertEquals(0.0F, new Randomizer().getFloatInRange(-10.0F, -15.0F));
        }
    }

    @Nested
    class GetFloats {

        @Test
        void withCount_returnsCorrectSize() {
            assertEquals(10, new Randomizer().getFloats(10).size());
        }

        @Test
        void withCount_containsNoNulls() {
            assertTrue(new Randomizer().getFloats(10).stream().noneMatch(Objects::isNull));
        }

        @Test
        void withCount_returnsAllInUnitRange() {
            assertTrue(new Randomizer().getFloats(50).stream().allMatch(v -> 0.0F <= v && v <= 1.0F));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getFloats(count).isEmpty());
        }

        @Test
        void withCountAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getFloats(50, 100.0F).stream().allMatch(v -> 0.0F <= v && v < 100.0F));
        }

        @Test
        void withCountOriginAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getFloats(50, -15.0F, 10.0F).stream().allMatch(v -> -15.0F <= v && v < 10.0F));
        }
    }

    @Nested
    class GetFloatsInRange {

        @Test
        void withCountAndLimits_returnsCorrectSize() {
            assertEquals(50, new Randomizer().getFloatsInRange(50, -2.5F, 11.25F).size());
        }

        @Test
        void withCountAndLimits_returnsAllInRange() {
            assertTrue(new Randomizer().getFloatsInRange(50, -2.5F, 11.25F).stream().allMatch(v -> -2.5F <= v && v <= 11.25F));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getFloatsInRange(count, -2.5F, 11.25F).isEmpty());
        }
    }

    @Nested
    class GetDouble {

        @Test
        void withNoArgs_returnsValueInUnitRange() {
            double value = new Randomizer().getDouble();
            assertTrue(0.0D <= value && value <= 1.0D);
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getDouble(), new Randomizer(42L).getDouble());
        }

        @Nested
        class WithBound {

            @Test
            void withPositiveBound_returnsValueInRange() {
                double value = new Randomizer().getDouble(100.0D);
                assertTrue(0.0D <= value && value < 100.0D);
            }

            @ParameterizedTest
            @ValueSource(doubles = {0.0D, -0.001D, -100.0D})
            void withNonPositiveBound_returnsZero(double bound) {
                assertEquals(0.0D, new Randomizer().getDouble(bound));
            }
        }

        @Nested
        class WithOrigin {

            @Test
            void withValidOriginAndBound_returnsValueInRange() {
                double value = new Randomizer().getDouble(-15.0D, -10.0D);
                assertTrue(-15.0D <= value && value < -10.0D);
            }

            @Test
            void withBoundLesserThanOrigin_returnsZero() {
                assertEquals(0.0D, new Randomizer().getDouble(-15.0D, -25.0D));
            }
        }
    }

    @Nested
    class GetDoubleInRange {

        @Test
        void withNegativeAndPositiveLimits_returnsValueInRange() {
            double value = new Randomizer().getDoubleInRange(-5.0D, 5.0D);
            assertTrue(-5.0D <= value && value <= 5.0D);
        }

        @Test
        void withPositiveLimits_returnsValueInRange() {
            double value = new Randomizer().getDoubleInRange(20.0D, 30.0D);
            assertTrue(20.0D <= value && value <= 30.0D);
        }

        @Test
        void withNegativeLimits_returnsValueInRange() {
            double value = new Randomizer().getDoubleInRange(-10.0D, -5.0D);
            assertTrue(-10.0D <= value && value <= -5.0D);
        }

        @Test
        void withEqualLimits_returnsThatValue() {
            assertEquals(-10.0D, new Randomizer().getDoubleInRange(-10.0D, -10.0D));
        }

        @Test
        void withMaxLesserThanMin_returnsZero() {
            assertEquals(0.0D, new Randomizer().getDoubleInRange(-10.0D, -15.0D));
        }
    }

    @Nested
    class GetDoubles {

        @Test
        void withCount_returnsCorrectSize() {
            assertEquals(10, new Randomizer().getDoubles(10).size());
        }

        @Test
        void withCount_containsNoNulls() {
            assertTrue(new Randomizer().getDoubles(10).stream().noneMatch(Objects::isNull));
        }

        @Test
        void withCount_returnsAllInUnitRange() {
            assertTrue(new Randomizer().getDoubles(50).stream().allMatch(v -> 0.0D <= v && v <= 1.0D));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getDoubles(count).isEmpty());
        }

        @Test
        void withCountAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getDoubles(50, 10.0D).stream().allMatch(v -> 0.0D <= v && v < 10.0D));
        }

        @Test
        void withCountOriginAndBound_returnsAllInRange() {
            assertTrue(new Randomizer().getDoubles(50, -15.0D, 10.0D).stream().allMatch(v -> -15.0D <= v && v < 10.0D));
        }
    }

    @Nested
    class GetDoublesInRange {

        @Test
        void withCountAndLimits_returnsCorrectSize() {
            assertEquals(50, new Randomizer().getDoublesInRange(50, -2.5D, 11.25D).size());
        }

        @Test
        void withCountAndLimits_returnsAllInRange() {
            assertTrue(new Randomizer().getDoublesInRange(50, -2.5D, 11.25D).stream().allMatch(v -> -2.5D <= v && v <= 11.25D));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -100})
        void withNonPositiveCount_returnsEmptyList(int count) {
            assertTrue(new Randomizer().getDoublesInRange(count, -2.5D, 11.25D).isEmpty());
        }
    }

    @Nested
    class GetGaussian {

        @Test
        void withNoArgs_returnsFiniteValue() {
            assertTrue(Double.isFinite(new Randomizer().getGaussian()));
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getGaussian(), new Randomizer(42L).getGaussian());
        }

        @Test
        void withMeanAndStdDev_returnsFiniteValue() {
            assertTrue(Double.isFinite(new Randomizer().getGaussian(10.0D, 2.0D)));
        }

        @Test
        void withMeanAndStdDev_clustersAroundMean() {
            Randomizer r = new Randomizer(0L);
            double avg = IntStream.range(0, 10_000).mapToDouble(i -> r.getGaussian(50.0D, 5.0D)).average().orElse(Double.NaN);
            assertTrue(Math.abs(avg - 50.0D) < 1.0D, "Expected average near 50.0 but was " + avg);
        }

        @Test
        void withConstraint_returnsValueAtOrBelowBound() {
            Randomizer r = new Randomizer();
            assertTrue(IntStream.range(0, 100).mapToDouble(i -> r.getGaussian(10.0D, 2.0D, 11.5D)).allMatch(v -> v <= 11.5D));
        }
    }

    @Nested
    class GetGaussianInt {

        @Test
        void withNoArgs_doesNotThrow() {
            assertDoesNotThrow(() -> new Randomizer().getGaussianInt());
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getGaussianInt(), new Randomizer(42L).getGaussianInt());
        }

        @Test
        void withMeanAndStdDev_doesNotThrow() {
            assertDoesNotThrow(() -> new Randomizer().getGaussianInt(10.0D, 2.0D));
        }

        @Test
        void withMeanAndStdDev_clustersAroundMean() {
            Randomizer r = new Randomizer(0L);
            double avg = IntStream.range(0, 10_000).mapToDouble(i -> r.getGaussianInt(50.0D, 5.0D)).average().orElse(Double.NaN);
            assertTrue(Math.abs(avg - 50.0D) < 1.0D, "Expected average near 50.0 but was " + avg);
        }

        @Test
        void withConstraint_returnsValueAtOrBelowBound() {
            Randomizer r = new Randomizer();
            assertTrue(IntStream.range(0, 100).map(i -> r.getGaussianInt(10.0D, 2.0D, 11)).allMatch(v -> v <= 11));
        }
    }

    @Nested
    class GetCharBasedOnWeight {

        @Test
        void withWeightedChars_returnsOneOfTheChars() {
            WeightedChar[] weighted = {new WeightedChar('a', 0.35D), new WeightedChar('b', 0.4D), new WeightedChar('c', 0.25D)};
            Randomizer r = new Randomizer();
            assertTrue(IntStream.range(0, 100).mapToObj(i -> r.getCharBasedOnWeight(weighted)).allMatch(c -> c == 'a' || c == 'b' || c == 'c'));
        }

        @Test
        void withWeightedChars_coversAllCharsEventually() {
            WeightedChar[] weighted = {new WeightedChar('a', 0.35D), new WeightedChar('b', 0.4D), new WeightedChar('c', 0.25D)};
            Randomizer r = new Randomizer();
            Set<Character> seen = IntStream.range(0, 300).mapToObj(i -> r.getCharBasedOnWeight(weighted)).collect(Collectors.toSet());
            assertEquals(Set.of('a', 'b', 'c'), seen);
        }

        @Test
        void withSameSeed_returnsIdenticalSequence() {
            WeightedChar[] weighted = {new WeightedChar('a', 0.35D), new WeightedChar('b', 0.4D), new WeightedChar('c', 0.25D)};
            Randomizer r1 = new Randomizer(42L);
            Randomizer r2 = new Randomizer(42L);
            List<Character> seq1 = IntStream.range(0, 20).mapToObj(i -> r1.getCharBasedOnWeight(weighted)).collect(Collectors.toList());
            List<Character> seq2 = IntStream.range(0, 20).mapToObj(i -> r2.getCharBasedOnWeight(weighted)).collect(Collectors.toList());
            assertEquals(seq1, seq2);
        }

        @Test
        void withSingleEntry_alwaysReturnsThatChar() {
            WeightedChar[] weighted = {new WeightedChar('z', 1.0D)};
            Randomizer r = new Randomizer();
            assertTrue(IntStream.range(0, 100).mapToObj(i -> r.getCharBasedOnWeight(weighted)).allMatch(c -> c == 'z'));
        }

        @Test
        void withFullWeightOnOneChar_alwaysReturnsThatChar() {
            WeightedChar[] weighted = {new WeightedChar('a', 0.0D), new WeightedChar('b', 1.0D)};
            Randomizer r = new Randomizer();
            assertTrue(IntStream.range(0, 100).mapToObj(i -> r.getCharBasedOnWeight(weighted)).allMatch(c -> c == 'b'));
        }
    }

    @Nested
    class GetElement {

        @Nested
        class FromArray {

            @Test
            void withStringArray_returnsContainedElement() {
                String[] values = {"alpha", "beta", "gamma"};
                assertTrue(List.of(values).contains(new Randomizer().getElement(values)));
            }

            @Test
            void withSingleElementArray_alwaysReturnsThatElement() {
                String[] values = {"only"};
                Randomizer r = new Randomizer();
                assertTrue(IntStream.range(0, 20).mapToObj(i -> r.getElement(values)).allMatch(v -> v.equals("only")));
            }

            @Test
            void withSmallArray_coversAllValuesEventually() {
                String[] values = {"a", "b", "c"};
                Randomizer r = new Randomizer();
                Set<String> seen = IntStream.range(0, 300).mapToObj(i -> r.getElement(values)).collect(Collectors.toSet());
                assertEquals(Set.of(values), seen);
            }

            @Test
            void withSameSeed_returnsIdenticalValue() {
                String[] values = {"alpha", "beta", "gamma"};
                assertEquals(new Randomizer(42L).getElement(values), new Randomizer(42L).getElement(values));
            }
        }

        @Nested
        class FromList {

            @Test
            void withIntegerList_returnsContainedElement() {
                List<Integer> values = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
                assertTrue(values.contains(new Randomizer().getElement(values)));
            }

            @Test
            void withSingleElementList_alwaysReturnsThatElement() {
                List<Integer> values = List.of(42);
                Randomizer r = new Randomizer();
                assertTrue(IntStream.range(0, 20).mapToObj(i -> r.getElement(values)).allMatch(v -> v == 42));
            }

            @Test
            void withSameSeed_returnsIdenticalValue() {
                List<Integer> values = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
                assertEquals(new Randomizer(42L).getElement(values), new Randomizer(42L).getElement(values));
            }
        }

        @Nested
        class FromSet {

            @Test
            void withIntegerSet_returnsContainedElement() {
                Set<Integer> values = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toSet());
                assertTrue(values.contains(new Randomizer().getElement(values)));
            }

            @Test
            void withSingleElementSet_alwaysReturnsThatElement() {
                Set<Integer> values = Set.of(99);
                Randomizer r = new Randomizer();
                assertTrue(IntStream.range(0, 20).mapToObj(i -> r.getElement(values)).allMatch(v -> v == 99));
            }
        }

        @Nested
        class FromMap {

            @Test
            void withIntegerMap_returnsValidKey() {
                Map<Integer, Integer> values = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toMap(i -> i, i -> i));
                assertTrue(values.containsKey(new Randomizer().getElement(values)));
            }

            @Test
            void withSingleEntryMap_alwaysReturnsThatKey() {
                Map<Integer, Integer> values = Map.of(7, 7);
                Randomizer r = new Randomizer();
                assertTrue(IntStream.range(0, 20).mapToObj(i -> r.getElement(values)).allMatch(v -> v == 7));
            }

            @Test
            void withSameSeed_returnsIdenticalKey() {
                Map<Integer, Integer> values = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toMap(i -> i, i -> i));
                assertEquals(new Randomizer(42L).getElement(values), new Randomizer(42L).getElement(values));
            }
        }
    }

    @Nested
    class GetEnum {

        @Test
        void withDayOfWeek_returnsValidEnumValue() {
            assertTrue(List.of(DayOfWeek.values()).contains(new Randomizer().getEnum(DayOfWeek.class)));
        }

        @Test
        void withDayOfWeek_coversAllValuesEventually() {
            Randomizer r = new Randomizer();
            Set<DayOfWeek> seen = IntStream.range(0, 300).mapToObj(i -> r.getEnum(DayOfWeek.class)).collect(Collectors.toSet());
            assertEquals(Set.of(DayOfWeek.values()), seen);
        }

        @Test
        void withSameSeed_returnsIdenticalValue() {
            assertEquals(new Randomizer(42L).getEnum(DayOfWeek.class), new Randomizer(42L).getEnum(DayOfWeek.class));
        }
    }
}
