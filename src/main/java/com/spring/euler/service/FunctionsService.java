package com.spring.euler.service;

import com.spring.euler.domain.Response;
import reactor.core.publisher.Mono;

public interface FunctionsService {
    Mono<Response> isPrime(Integer n);
    Mono<Response> findNextPrime(Integer n);

    Mono<Response> isPalindrome(Integer value);

    Mono<Response> isPandigital(String value);

    Mono<Response> lowestCommonMultiple(Integer x, Integer y);
    Mono<Response> greatestCommonDivisor(Integer x, Integer y);

    Mono<Response> sieveOfEratosthenes(Integer x);

    Mono<Response> triangleNumber(Integer value);

    Mono<Response> findDivisors(Integer value);

    Mono<Response> isAbundant(Integer value);

    Mono<Response> binomialExpansion(Integer n, Integer k);

    Mono<Response> factorial(Integer value);

    Mono<Response> readAllFileLines(String absolutePath);

    Mono<Response> permutations(String permuteObject);
}
