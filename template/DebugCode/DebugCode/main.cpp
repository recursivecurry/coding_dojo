//
//  main.cpp
//  DebugCode
//
//  Created by jongsoo.lee on 2014. 10. 1..
//  Copyright (c) 2014년 jongsoo.lee. All rights reserved.
//

#include <iostream>
#include <vector>

using namespace std;

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    vector<int> a{1, 2, 3, 4, 5};
    
    for (auto x : a) {
        cout << x;
    }
    return 0;
}
