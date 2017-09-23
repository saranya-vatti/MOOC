function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

K = size(Theta2, 1);
% K is 5000

% m is 5000
% X is 5000 X 400
a1 = [ones(m, 1) X];
% a1 is 5000 X 401
z2 = a1 * Theta1';
% Theta1 is 25 X 401
% z2 is 5000 X 25

a2 = sigmoid(z2);
% a2 is 5000 X 25
a2 = [ones(m, 1) a2];
% a2 is 5000 X 26
% Theta2 is 10 X 26
z3 = a2 * Theta2';
% z3 is 5000 X 10

a3 = sigmoid(z3); % a3 is 5000 X 10
h = a3; % h is 5000 X 10

y = eye(K)(y,:); % y is 5000 X 10

firstterm = -y .* log(h); % firstterm 5000 X 10
secondterm = (1 - y) .* log(1 - h); % secondterm 5000 X 10
J = sum(sum(firstterm - secondterm));
J = J/m;

tmp1=Theta1;
tmp1(:,1)=0;
tmp2=Theta2;
tmp2(:,1)=0;
J = J + ((lambda/(2 * m)) * (sum(sum(tmp1 .* tmp1)) + sum(sum(tmp2 .* tmp2))));

Delta1 = zeros(size(Theta1));
Delta2 = zeros(size(Theta2));
for t = 1:m
	a1 = [1 X(t,:)]; % a1 is 1 X 401
	z2 = a1 * Theta1'; % z2 is 1 X 25
	a2 = sigmoid(z2); % a2 is 1 X 25
	a2 = [1 a2]; % a2 is 1 X 26
	z3 = a2 * Theta2'; % z2 is 1 X 10
	a3 = sigmoid(z3); % a3 is 1 X 10

	d3 = a3 - y(t,:); % d3 is 1 X 10
	d2 = (d3 * Theta2)(2:end) .* sigmoidGradient(z2); % d2 is 1 X 25

	Delta2 = Delta2 + (d3' * a2); % Delta2 is 10 X 26
	Delta1 = Delta1 + (d2' * a1); % Delta1 is 25 X 401
end;

Theta1_grad = Delta1/m;
Theta2_grad = Delta2/m;

tmp1=Theta1;
tmp1(:,1)=0;
tmp2=Theta2;
tmp2(:,1)=0;
Theta1_grad = (Delta1/m) + ((lambda/m) * tmp1);
Theta2_grad = (Delta2/m) + ((lambda/m) * tmp2);

%theta(1, 1) = 0;
%J = J + (lambda/(2*m)) * sum(theta .* theta);

% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
