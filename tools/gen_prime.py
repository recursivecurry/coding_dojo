import argparse
import sys

# Sieve of Eratosthenes
# Code by David Eppstein, UC Irvine, 28 Feb 2002
# http://code.activestate.com/recipes/117119/

def gen_primes():
    """ Generate an infinite sequence of prime numbers.
    """
    # Maps composites to primes witnessing their compositeness.
    # This is memory efficient, as the sieve is not "run forward"
    # indefinitely, but only as long as required by the current
    # number being tested.
    #
    D = {}  

    # The running integer that's checked for primeness
    q = 2  

    while True:
        if q not in D:
            # q is a new prime.
            # Yield it and mark its first multiple that isn't
            # already marked in previous iterations
            # 
            yield q        
            D[q * q] = [q]
        else:
            # q is composite. D[q] is the list of primes that
            # divide it. Since we've reached q, we no longer
            # need it in the map, but we'll mark the next 
            # multiples of its witnesses to prepare for larger
            # numbers
            # 
            for p in D[q]:
                D.setdefault(p + q, []).append(p)
            del D[q]
        q += 1

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate prime number array")
    parser.add_argument('n', metavar='N', nargs=1, type=int, help="Limit value")
    group = parser.add_mutually_exclusive_group()
    group.add_argument('--count', action='store_const', const=True,
            default=False, help='limit number of generated prime number (default)')
    group.add_argument('--value', action='store_const', const=True,
            default=False, help='limit max value of generated prime number')

    args = parser.parse_args()
    
    if args.value:
        limit = args.n[0]
    else:
        limit = args.n[0]-2

    prime = iter(gen_primes())
    sys.stdout.write("{"+str(prime.next()))
    for idx, val in enumerate(prime):
        if args.value and limit < val:
            break
        elif limit < idx:
            break
        sys.stdout.write(", "+str(val))
    print("}")
