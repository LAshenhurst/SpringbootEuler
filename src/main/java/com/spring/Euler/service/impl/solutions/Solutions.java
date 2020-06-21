package com.spring.Euler.service.impl.solutions;

import com.spring.Euler.service.impl.solutions.Impl.FirstSolutionsImpl;
import com.spring.Euler.service.impl.solutions.Impl.SecondSolutionsImpl;
import org.springframework.stereotype.Component;

@Component
public class Solutions implements FirstSolutionsImpl, SecondSolutionsImpl {
    // This is hardly "good practice" but I think it's better than a single class with 1000+ lines as more methods are added.
}