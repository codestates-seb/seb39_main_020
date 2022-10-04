import * as React from 'react';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import { Stack } from '@mui/material';

  const Alert = React.forwardRef(function Alert(props, ref) {
      return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
  });

  const AVAILABLE_SEVERITY = ["error","info","success","warning"];

  export default function SnackBarMessage(props) {
    const {message, severity, openSnackbar, setOpenSnackbar} = props;

    React.useEffect(() => {
      setOpenSnackbar(openSnackbar)
    },[setOpenSnackbar, openSnackbar]);

    const handleClose = () => setOpenSnackbar(false);
  
    return (
      <Stack spacing={2} sx={{ width: '100%' }}>
        <Snackbar open={openSnackbar} autoHideDuration={6000} onClose={handleClose}>
          <Alert severity={AVAILABLE_SEVERITY.indexOf(severity) > -1 ? severity : "success" } sx={{ width: '100%' }}>
            {message}
          </Alert>
        </Snackbar>
      </Stack>
    );
  }