N, K = map(int,input().split())
lst = list(map(int,input().split()))
slst = [0]*(N+1)

ans = -10**9

for i in range(1,N+1):
    slst[i] = lst[i-1] + slst[i-1]
# print(slst)

# for (int i = 0 ; i < N-K; i++)
for start in range(N-K+1):
    ans = max(ans, slst[start+K]-slst[start])

print(ans)