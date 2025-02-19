// src/ex5_dispatch/Counter.tsx
import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { increment, decrement, incrementByAmount, reset } from './counterSlice';
import { RootState, useAppDispatch } from './store';

const Counter: React.FC = () => {
  const dispatch = useAppDispatch();
  const count = useSelector((state: RootState) => state.counter.value);
  const [incrementAmount, setIncrementAmount] = useState(0);

  return (
    <div>
      <h1>Counter: {count}</h1>
      <button onClick={() => dispatch(increment())}>Increment</button>
      <button onClick={() => dispatch(decrement())}>Decrement</button>
      <button onClick={() => dispatch(reset())}>Reset</button>
      <div>
        <input
          type="number"
          value={incrementAmount}
          onChange={(e) => setIncrementAmount(Number(e.target.value))}
        />
        <button onClick={() => dispatch(incrementByAmount(incrementAmount))}>
          Increment by Amount
        </button>
      </div>
    </div>
  );
};

export default Counter;
