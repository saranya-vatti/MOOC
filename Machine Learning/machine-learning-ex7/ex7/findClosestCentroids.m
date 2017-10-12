function idx = findClosestCentroids(X, centroids)
%FINDCLOSESTCENTROIDS computes the centroid memberships for every example
%   idx = FINDCLOSESTCENTROIDS (X, centroids) returns the closest centroids
%   in idx for a dataset X where each row is a single example. idx = m x 1 
%   vector of centroid assignments (i.e. each entry in range [1..K])
%

% Set K
K = size(centroids, 1);

% You need to return the following variables correctly.
idx = zeros(size(X,1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Go over every example, find its closest centroid, and store
%               the index inside idx at the appropriate location.
%               Concretely, idx(i) should contain the index of the centroid
%               closest to example i. Hence, it should be a value in the 
%               range 1..K
%
% Note: You can use a for-loop over the examples to compute this.
%

% X = 300 X 2
% centroids = 3 X 2

% With extra for loop:
%for Xrow=1:size(X)(1)
%  mindist = -1;
%  for centroidrow=1:size(centroids)(1)
%    diffvec=X(Xrow,:) - centroids(centroidrow,:);
%    dist = sqrt(diffvec * diffvec');
%    if(mindist == -1 || mindist > dist)
%      mindist=dist; 
%      idx(Xrow) = centroidrow;
%    end;
%  end;
%end;

for Xrow=1:size(X)(1)
  mindist = -1;
  diffvec = centroids-X(Xrow,:);
  dist = sqrt(sumsq (diffvec, 2)); % 3X1
  [minval, row] = min(dist);
  idx(Xrow) =  row;
end;

% =============================================================

end

