import { ThemeProvider } from '@emotion/react';
import { createTheme, CssBaseline } from '@mui/material';
import routes from './configs/rootRoutes';
import { useRoutes } from 'react-router';

const MyRoute = () => {
  const myRoutes = useRoutes(routes);
  return myRoutes;
}

const theme = createTheme();

function App() {
  return (
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <MyRoute/>
      </ThemeProvider>
  )
}

export default App;
