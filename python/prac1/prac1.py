#!/usr/bin/env python3.5
import sys
import numpy as np
import random

def load_data():
    fo = open("IntegerArray.txt")
    data = fo.read()
    fo.close
    array = data.split('\n')
    intarray = []
    for elem in array[0:-1]:
        intarray.append(int(elem))
    return intarray

def split(xs):
    n = len(xs)
    if (n == 1):
        return xs, []
    else:
        half = round(n/2)
        half = int(half)
        A = xs[0:half]
        B = xs[half:n+1]
        return A, B

def merge(B, C):
    len_b = len(B)
    len_c = len(C)
    D = []
    i = 0
    j = 0
    for k in range(len_b + len_c):
        if (i == len_b):
            D.append(C[j])
            j = j + 1
        elif (j == len_c):
            D.append(B[i])
            i = i + 1            
        elif B[i] > C[j]:
            D.append(C[j])
            j = j + 1
        elif B[i] < C[j]:
            D.append(B[i])
            i = i + 1
    return D

def merge_count_inv(B, C):
    len_b = len(B)
    len_c = len(C)
    D = []
    i = 0
    j = 0
    num_inv = 0
    for k in range(len_b + len_c):
        if (i == len_b):
            D.append(C[j])
            j = j + 1
        elif (j == len_c):
            D.append(B[i])
            i = i + 1            
        elif B[i] > C[j]:
            D.append(C[j])
            j = j + 1
            num_inv = num_inv + (len_b - i)
        elif B[i] < C[j]:
            D.append(B[i])
            i = i + 1
    return num_inv

def sort(xs):
    if len(xs) == 0:
        return []
    elif len(xs) == 1:
        return xs
    else:
        A, B = split(xs)
        return merge(sort(A), sort(B))

def sort_and_count(A, n):
    if n == 1:
        return 0
    else:
        B, C = split(A)
        X = sort_and_count(B, len(B))
        Y = sort_and_count(C, len(C))
        Z = count_split_inv(A, n)
        return X + Y + Z

def count_split_inv(A, n):
    B,C = split(A)
    B_sorted = sort(B)
    C_sorted = sort(C)
    return merge_count_inv(B_sorted,C_sorted)
    
if __name__ == "__main__":
    sys.setrecursionlimit(1000000)
    data = load_data()
    small = [1,6,3,2,4,5]
    print(sort_and_count(data, len(data)))
    



