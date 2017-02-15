class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int length = prices.size();
        if(length < 2) return 0;

        int one_sell = 0;
        int one_keep = -prices[0];
        int none_buy = -prices[0];
        int none_keep = 0;
        for (int i=1; i<length; i++) {
            one_keep = (one_keep > none_buy ? one_keep : none_buy);
            none_buy = -prices[i] + none_keep;
            none_keep = one_sell > none_keep ? one_sell : none_keep;
            one_sell = prices[i] + one_keep;
        }
        return one_sell > none_keep ? one_sell : none_keep;
    }
};
