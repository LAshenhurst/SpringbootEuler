package com.spring.euler.controller;

import com.spring.euler.domain.Response;
import com.spring.euler.service.FunctionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Api(tags = {"Functions"})
@RequiredArgsConstructor
@RequestMapping("/v1/functions")
public class FunctionsController {
    private final FunctionsService functionsService;

    @GetMapping("/prime")
    @ApiOperation(value = "Determine whether a provided integer is a prime number or not.")
    public Mono<Response> isPrime(@Valid @RequestParam Integer value) {
        return functionsService.isPrime(value);
    }

    @GetMapping("/next-prime")
    @ApiOperation(value = "Find the first prime number larger than a provided integer.")
    public Mono<Response> findNextPrime(@Valid @RequestParam Integer value) { return functionsService.findNextPrime(value); }

    @GetMapping("/palindrome")
    @ApiOperation(value = "Determine whether a provided integer is a palindrome")
    public Mono<Response> isPalindrome(@Valid @RequestParam Integer value) { return functionsService.isPalindrome(value); }

    @GetMapping("/lowest-common-multiple")
    @ApiOperation(value = "Determine the lowest common multiple of two provided integers x and y")
    public Mono<Response> lowestCommonMultiple(@Valid @RequestParam Integer x, @Valid @RequestParam Integer y) { return functionsService.lowestCommonMultiple(x, y); }

    @GetMapping("/greatest-common-divisor")
    @ApiOperation(value = "Determine the greatest common divisor of two provided integers x and y")
    public Mono<Response> greatestCommonDivisor(@Valid @RequestParam Integer x, @Valid @RequestParam Integer y) { return functionsService.greatestCommonDivisor(x, y); }

    @GetMapping("/sieve-of-eratosthenes")
    @ApiOperation(value = "Use the Sieve of Eratosthenes algorithm to determine all prime numbers below a provided integer")
    public Mono<Response> sieveOfEratosthenes(@Valid @RequestParam Integer value) { return functionsService.sieveOfEratosthenes(value); }

    @GetMapping("/triangle-number")
    @ApiOperation(value = "Determine the triangle number based on the provided integer")
    public Mono<Response> triangleNumber(@Valid @RequestParam Integer value) { return functionsService.triangleNumber(value); }

    @GetMapping("/divisors")
    @ApiOperation(value = "Determine all divisors of the provided integer")
    public Mono<Response> findDivisors(@Valid @RequestParam Integer value) { return functionsService.findDivisors(value); }

    @GetMapping("/abundant")
    @ApiOperation(value = "Determine whether a provided integer is an abundant number")
    public Mono<Response> isAbundant(@Valid @RequestParam Integer value) {
        return functionsService.isAbundant(value);
    }

    @GetMapping("/binomial-expansion")
    @ApiOperation(value = "Determine the binomial expansion of two given integers n and k")
    public Mono<Response> binomialExpansion(@Valid @RequestParam Integer n, @Valid @RequestParam Integer k) { return functionsService.binomialExpansion(n, k); }

    @GetMapping("/factorial")
    @ApiOperation(value = "Calculate the factorial of a given integer.")
    public Mono<Response> factorial(@Valid @RequestParam Integer value) {
        return functionsService.factorial(value);
    }

    @GetMapping("/read-all-lines")
    @ApiOperation(value = "Read all lines of a file both local and accessible by this server by providing the absolute path")
    public Mono<Response> readAllLines(@Valid @RequestParam String absolutePath) { return functionsService.readAllFileLines(absolutePath); }

    @GetMapping("/permutations")
    @ApiOperation(value = "Determine all permutations of the provided string")
    public Mono<Response> permutations(@Valid @RequestParam String permuteObject) { return functionsService.permutations(permuteObject); }

    @GetMapping("/pandigital")
    @ApiOperation(value = "Determine whether a given string is a pandigital number")
    public Mono<Response> pandigital(@Valid @RequestParam String value) { return functionsService.isPandigital(value); }
}