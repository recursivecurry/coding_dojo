#include <cstddef>
#include <cstdio>
#include <map>
#include <string>
#include <cstring>
#include <cstdlib>

using namespace std;

class TreeNode {
public:
	TreeNode *children[26];// = {nullptr, };
	char text[201];// = {0, };
	int length;// = 0;
	int value;// = 0;
	TreeNode() {
		memset(children, 0, sizeof(children));
		memset(text, 0, sizeof(text));
		length = 0;
		value = 0;
	};
};

static TreeNode *maxNode[200] = {0, };

TreeNode ** getChild(TreeNode *current, char ch) {
	return &(current->children[ch-'a']);
}

void updateMax(TreeNode *target) {
	if (0 == maxNode[target->length-1])
		maxNode[target->length-1] = target;
	else if (maxNode[target->length-1]->value < target->value)
		maxNode[target->length-1] = target;
	else if (strcmp(target->text, maxNode[target->length-1]->text)<0)
		maxNode[target->length-1] = target;
}

void updateTree(TreeNode *root, char *word) {
	TreeNode *current = root;
	char *pos = word;
///	printf("WORD: %s\n", word);
	while ('\n' != *pos && '\0' != *pos) {
///		printf("POS: %c\n", *pos);
		TreeNode **child = getChild(current, *pos);
		if (*child) {
			(*child)->value += 1;
			(*child)->value += 1;
///			printf("UPDATE %s : %d\n", (*child)->text, (*child)->value);
		} else {
			*child = new TreeNode;
			(*child)->length = current->length + 1;
			memcpy((*child)->text, word, (*child)->length);
			(*child)->value += 1;
///			printf("NEW %s : %d\n", (*child)->text, (*child)->value);
		}
		updateMax(*child);
		current = *child;
		pos++;
	}
	return;
}

int main(int argc, char *argv[]) {
	int C;

	scanf("%d", &C);
///	printf("%d\n", C);

	while (C--) {
		int N, M;
		char WORD[202] = {0, };
		TreeNode root;

///		printf("%lu\n", sizeof(maxNode));
		memset(maxNode, 0, sizeof(maxNode));

		scanf("%d %d", &N, &M);
///		printf("%d %d\n", N, M);

		while (N--) {
			//fgets(WORD, sizeof(WORD), stdin);
			scanf("%s", WORD);
///			printf("%s\n", WORD);
			updateTree(&root, WORD);
		}

		int cur = 0;
		while(maxNode[cur]) {
			printf("%s\n", maxNode[cur]->text);
			cur++;
		}

	}
}