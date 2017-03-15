#include <stdio.h>
#include <math.h>

void greedy();

float get_change_money();

int convert_to_int(float f);

int get_minimum_coins(int change_money);

float get_positive_float();

int convert_change_money_to_int(float change_money);

int get_nearest_number (int i);

int main(void) {
	greedy();
}

void greedy() {
	
	//get change money from console
	float chg_money = get_change_money();
	//convert change money to int
	int in_chg_money = convert_change_money_to_int(chg_money);
	//get the minimum number of coins
	int number_coins = get_minimum_coins(in_chg_money);
	//print result
	printf("Minimum coins: %i", number_coins);
	
}

int get_nearest_number(int i) {
	if (i >= 25) return 25;
	if (i >= 10) return 10;
	if (i >= 5) return 5;
	if (i >= 1) return 1;
	return 0;
}

int convert_change_money_to_int(float change_money) {
	// convert from $ to cent
	float cent_number = 100 * change_money;
	// convert float to int
	int i = convert_to_int (cent_number);
	return i;
}

int get_minimum_coins(int change_money) {
	if (0 == change_money) return 0;
	if (25 == change_money || 10 == change_money || 5 == change_money) return 1;
	if (change_money < 5) return change_money;
	// get nearest coin number of change_money
	int nearest_number = get_nearest_number(change_money);
	// get remain value after minus the nearest number of change money. Exg: change_money: 35 --> nearest: 25 --> remain : 10
	int remain = change_money - nearest_number;
	return 1 + get_minimum_coins(remain);
}

int convert_to_int(float f) {
	int i = (int) roundf(f);
	return i;
}

float get_change_money () {
	printf("O hai, How much change is owed?\n");
	float f = get_positive_float();
	return f;
}

float get_positive_float() {
	int scan_result;
	float f;
	do {
		scan_result = scanf("%f", &f);
		if (scan_result <=0) {
			while (fgetc(stdin) != '\n');
		}
		if (scan_result <=0 || f < 0) {
			printf("Retry: ");
		}
	} while (scan_result <= 0 || f < 0);
	return f;
}