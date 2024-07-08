#include <iostream>
#include <cmath>
#include <vector>
#include <climits>
 
using namespace std;
 
class SegmentTree {
    vector<int> st;
    vector<int> arr;
    int size;
 
public:
    SegmentTree(vector<int>& arr) {
        this->arr = arr;
        int n = arr.size();
        int height = ceil(log2(n));
        size = 2 * pow(2, height) - 1;
        st.resize(size);
        buildSegmentTree(0, n - 1, 0);
    }
 
    int buildSegmentTree(int ss, int se, int si) {
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }
        int mid = ss + (se - ss) / 2;
        st[si] = max(buildSegmentTree(ss, mid, 2 * si + 1),
                     buildSegmentTree(mid + 1, se, 2 * si + 2));
        return st[si];
    }
 
    void update(int k, int u) {
        updateUtil(0, arr.size() - 1, 0, k, u);
    }
 
    void updateUtil(int ss, int se, int si, int k, int u) {
        if (ss == se) {
            arr[k] = u;
            st[si] = u;
            return;
        }
        int mid = ss + (se - ss) / 2;
        if (k <= mid)
            updateUtil(ss, mid, 2 * si + 1, k, u);
        else
            updateUtil(mid + 1, se, 2 * si + 2, k, u);
        st[si] = max(st[2 * si + 1], st[2 * si + 2]);
    }
 
    int query(int qs, int qe) {
        return queryUtil(0, arr.size() - 1, qs, qe, 0);
    }
 
    int queryUtil(int ss, int se, int qs, int qe, int si) {
        if (qs <= ss && qe >= se)
            return st[si];
        if (qe < ss || qs > se)
            return INT_MAX;
        int mid = ss + (se - ss) / 2;
        return max(queryUtil(ss, mid, qs, qe, 2 * si + 1),
                   queryUtil(mid + 1, se, qs, qe, 2 * si + 2));
    }
};
 
int main() {
    int n, q;
    cin >> n >> q;
    vector<int> arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];
 
    SegmentTree segmentTree(arr);
 
    for (int i = 0; i < q; i++) {
        int type;
        cin >> type;
        if (type == 1) {
            int k, u;
            cin >> k >> u;
            segmentTree.update(k - 1, u);
        } else if (type == 2) {
            int a, b;
            cin >> a >> b;
            int maxVal = segmentTree.query(a - 1, b - 1);
            cout << maxVal << endl;
        }
    }
 
    return 0;
}
