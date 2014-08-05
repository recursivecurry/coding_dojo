#include <iostream>
#include <map>
#include <vector>
#include <string>

using namespace std;

class BinaryCode {
private:
    string message;
    map<int, int> cache[2];
    int Q(int pos);
    int P(int start, int pos);
    string messageP(int start);
public:
    vector <string> decode(string message);
};

vector <string> BinaryCode::decode(string message) {

    vector <string> result;

    this->message = message;


    return result;
}

string BinaryCode::messageP(int start) {

    string newMessage = "";

    return newMessage;
}

int BinaryCode::P(int pos, int start) {
    map<int, int>::iterator it;
    it =  this->cache[start].find(pos);
    if (cache[start].end() != it)
        return it->second;

    if ( (pos<0) || (this->message.length() <= pos) ) {
        this->cache[start][pos] = 0;
        return 0;
    } else if (0 == pos) {
        this->cache[start][pos] = start;
        return start;
    } else {
        int p = Q(pos-1) - P(pos-2, start) - P(pos-1, start);
        this->cache[start][pos] = p;
        return p;
    }
}

int BinaryCode::Q(int pos) {
    return this->message[pos] - '0';
}

int main (int argc, char *argv[]) {

    return 0;
}