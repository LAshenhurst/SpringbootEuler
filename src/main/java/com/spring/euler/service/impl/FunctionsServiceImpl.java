package com.spring.euler.service.impl;

import com.spring.euler.domain.Response;
import com.spring.euler.domain.TimedSolution;
import com.spring.euler.domain.mappers.ResponseMapper;
import com.spring.euler.helper.*;
import com.spring.euler.service.FunctionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class FunctionsServiceImpl implements FunctionsService {
    private final ResponseMapper responseMapper;

    private Mono<Response> createResponse(String task, Object answer, String notes, String computeTime) {
        return Mono.just(responseMapper.generate(task, answer, notes, false, computeTime));
    }

    public Mono<Response> isPrime(Integer n) {
        String task = "Determine whether the number " + n + " is prime.";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.isPrime(n));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> isPalindrome(Integer value) {
        String task = "Determine whether " + value + " is a palindrome.";
        TimedSolution timedSolution = TimerHelper.run(() -> StringHelper.isPalindrome(String.valueOf(value)));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> isPandigital(String value) {
        String task = "Determine whether " + value + " is pandigital.";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.isPandigital(value));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> findNextPrime(Integer n) {
        String task = "Determine first prime number greater than " + n + ".";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.findNextPrime(n));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> lowestCommonMultiple(Integer x, Integer y) {
        String task = "Determine the lowest common multiple of " + x + " and " + y + ".";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.lowestCommonMultiple(x, y));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> greatestCommonDivisor(Integer x, Integer y) {
        String task = "Determine the greatest common divisor of " + x + " and " + y + ".";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.greatestCommonDivisor(x, y));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> sieveOfEratosthenes(Integer value) {
        String task = "Use the Sieve of Eratosthenes method to determine all prime numbers below " + value + ".";
        String notes = "This method should be fast, but it is not the fastest possible. Please be aware of this, and the possible number of results, when using this method.";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.sieveOfEratosthenes(value));
        return createResponse(task, timedSolution.getAnswer(), notes, timedSolution.getComputeTime());
    }

    public Mono<Response> triangleNumber(Integer value) {
        String task = "Generate the " + value + "th triangle number.";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.triangleNumber(value));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> findDivisors(Integer value) {
        String task = "Generate all divisors of " + value + ".";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.findDivisors(value));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> isAbundant(Integer value) {
        String task = "Determine whether " + value + " is an abundant number.";
        String notes = "An abundant number is one whether the sum of its divisors is greater than itself.";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.isAbundant(value));
        return createResponse(task, timedSolution.getAnswer(), notes, timedSolution.getComputeTime());
    }

    public Mono<Response> binomialExpansion(Integer n, Integer k) {
        String task = "Calculate the binomial expansion of " + n + "C" + k + ".";
        TimedSolution timedSolution = TimerHelper.run(() -> MathsHelper.binomial(n, k));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> factorial(Integer value) {
        String task = "Calculate " + value + "!";
        String notes = "This method uses BigInteger to go beyond the 32-bit integer maximum, but be aware of performance concerns for extremely high values.";
        TimedSolution timedSolution = TimerHelper.run(() -> BigIntegerHelper.factorial(value));
        return createResponse(task, timedSolution.getAnswer(), notes, timedSolution.getComputeTime());
    }

    public Mono<Response> readAllFileLines(String absolutePath) {
        String task = "Attempt to read all lines of the file at this path: " + absolutePath;
        TimedSolution timedSolution = TimerHelper.run(() -> FilesHelper.readAllLines(absolutePath));
        return createResponse(task, timedSolution.getAnswer(), null, timedSolution.getComputeTime());
    }

    public Mono<Response> permutations(String permuteObject) {
        String task = "Generate all permutations of " + permuteObject + ".";
        String notes = "This method uses recursion, so please be aware of this when choosing values to submit.";
        TimedSolution timedSolution = TimerHelper.run(() -> StringHelper.permutations(permuteObject));
        return createResponse(task, timedSolution.getAnswer(), notes, timedSolution.getComputeTime());
    }
}