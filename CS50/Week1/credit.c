#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

void credit();
char* get_card_number();
void check_valid_card_number();
char* get_string();
int check_valid_number(char* number);

int main (void) {
	credit();
	
}

void credit() {
	// get card numbers from console
	char* numbers = get_card_number();
	// print result of checking if the card number is valid or not and which types of it
	//check_valid_card_number();
}

char* get_card_number() {
	char* number;
	//do {
		// get string from console
		printf("Number: ");
		number = get_string();
		//printf("%s",number);
		// check if each character is number	
	//} while (!check_valid_number(number));
	return number;
}

int check_valid_number(char* number) {
	if (number == NULL || strlen(number) == 0) return 0;
	for (int i = 0; i < strlen(number); i++) {
		char c = number[i];
		if (!isdigit(c)) return 0;
	}
	return 1;
}

char* get_string() {
	char result[256];
	char *string;
	fgets(result, 256, stdin);
	string = realloc(string, 256 * sizeof(char));
	strcat(string, result);
	return string;
}