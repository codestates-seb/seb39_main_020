import * as React from 'react';
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';

const LoaderComponent = (props) => {
  const { loading } = props;
  return (
    <Box
        sx={{
          display: (loading)?"flex":"none",
          position: 'absolute',
          top: 20,
          left: '90%',
          zIndex: 'tooltip',
        }}
      >
        <CircularProgress />
      </Box>
  );
}

export default LoaderComponent;