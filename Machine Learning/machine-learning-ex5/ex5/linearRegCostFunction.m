function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%

% X is 12 X 2
% theta is 2 X 1
% y is 12 X 1
% grad is 2 X 1

h = X * theta;
% h is 12X1
first = (1/(2*m)) * sum((h .- y) .* (h .- y));
tmp = theta(1,1);
theta(1, 1) = 0;
second = (lambda/(2*m)) * sum(theta .* theta);
J =  first + second;
theta(1,1) = tmp;

grad = (1/m)*(X' * (h .- y));
theta(1, 1) = 0;
grad = grad + ((lambda/m) * theta);

% =========================================================================

grad = grad(:);

end
