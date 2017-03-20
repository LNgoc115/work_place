#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

void vigenere(char* k);
char* get_string();
char* cypher(char* t, char* k);
char rotate(char c, int k);
int position(char* k, int i);
int convert_to_index(char c);
char resever_to_alpha (int i, char c);

int main (int agrc, char** agrv) {
	if (agrc != 2) {
		printf("Usage: a k \n");
		return 1;
	}
	//call vigenere
	vigenere(agrv[1]);
	return 0;
}

/**
encrypt plain text to cypher text using key k
**/
void vigenere(char* k) {
	//get input from console
	printf("plain_text:  ");
	char* plain_text = get_string();
	//get result of cypher method
	char* cypher_text = cypher(plain_text,k);
	//print result to console
	printf("cypher_text: %s", cypher_text);
}
//get string from user console
char* get_string() {
	char* s = malloc(255 * sizeof(char));
	return fgets(s, 255, stdin);
}
//encrypt t using key k by rotating each char in t by p(k[i]) position
//p(k[i]): A -> 0; B --> 1... 
//eg: plain_text: Zzabc,3,b
// k = ab --> p(k) = 01
//cypher_text: Z,0 --> Z; z,1 --> a; a,0 -->a; b,1 -->c; c,0 -->c;,3, --> ,3,; b,1 -->c
//cypher_text result: Zaacc,3,c
char* cypher(char* t, char* k) {
	//init variable cypher to store result after encryting
	char* cypher = malloc(strlen(t) * sizeof(char));
	//browse each char in t
	for (int i = 0, n = strlen(t), j = 0; i < n; i++) {
		// if t[i] is not alpha, store direct to cypher[i]
		if (!isalpha(t[i])) {
			cypher[i] = t[i];
		} else {
			//if t is alpha: convert k[i] to p(k[j]), rotate (t[i],p(k[j]))
			//j increase only if meeting the alpha character in t
			//store result in cypher[i]
			cypher[i] = rotate(t[i],position(k,j++));
		}
	}
	//return cypher
	return cypher;
}
/**
rotate char c by k position
**/
char rotate (char c, int k) {
	//convert char c to alphabet index
	//eg: a --> 0; b -->1
	int ind = convert_to_index(c);
	//calculate the cypher text index
	int cypher_index = (ind + k) % ('z' - 'a' + 1); //% 26
	//resever index to alphabet char. 2 cases: upper or lower 
	return resever_to_alpha(cypher_index, c); 
}

// convert char c to alpha index;
//eg: a --> 0; b--> 1
int convert_to_index (char c) {
	if (isupper(c)) {
		return c - 'A';
	} else {
		return c - 'a';
	}
}
// resever alpha index i to alphabet char
//eg: if c is Upper: 0 --> A; 1 --> B
char resever_to_alpha(int i, char c) {
	if (isupper(c)) {
		return i + 'A';
	} else {
		return i + 'a';
	}
}
//return the position from k and i
//eg: k = abc; i = 5; 5 % 3 = 2
//result: i = 5 --> k[i] = b (rotate) --> result = 1 (b --> 1)
int position(char* k, int i) {
	//find the index of k which to be used 
	int index = i % strlen(k);
	//convert k[index] to alpha index and return
	return convert_to_index(k[index]);
}