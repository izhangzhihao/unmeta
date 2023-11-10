package com.github.izhangzhihao.unmeta.example

/**
 * a tail-recursive function with keyword tailrec
 */
tailrec fun factorial(n: Long, a: Long = 1): Long {
    return if (n == 1L) a
    else factorial(n - 1, n * a)
}
