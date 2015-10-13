# HACKERRANK: Validating Email Addresses With a Filter
# https://www.hackerrank.com/challenges/validate-list-of-email-address-with-filter
import re

print(sorted(list(filter(lambda mail: True if re.fullmatch(r'[\w\-]+@[a-zA-Z\d]+\.[a-zA-Z]{1,3}', mail) else False, [input() for x in range(int(input()))]))))
