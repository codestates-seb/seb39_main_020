import { Container, Grid, Stack } from "@mui/material";
import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import LoaderComponent from "../components/loadingbarComponent";
import SnackBarMessage from "../components/snackbarComponent";
import Gnb from "./gnb";

const Layout = () => {
    const [openSnackbar, setOpenSnackbar] = useState(false);    
    const [snackbarMessage, setSnackbarMessage] = useState("");    
    const [snackbarSeverity, setSnackbarSeverity] = useState("danger");    
    const [loading, setLoading] = useState(false);

    const layoutConfig = {
        snackbar :{ 
            // AVAILABLE_SEVERITY = ["error","info","success","warning"]
            // usage : 사용할 페이지에서 useEffect({snackbar.open(메시지,테마),[조건,snackbar]})
            open: (message, severity = "success") => {
                setSnackbarMessage(message);
                setSnackbarSeverity(severity);
                setOpenSnackbar(true);
            }
        },
        loader : {//
            loading, setLoading
        }
    }

    return (
        <>
            <Stack spacing={1}>
                <Grid container spacing={1}>
                    <Gnb/>
                    <Container>
                        <Outlet context={layoutConfig}/>
                    </Container>
                </Grid>
            </Stack>
            <LoaderComponent {...layoutConfig.loader}/>
            <SnackBarMessage message={snackbarMessage} severity={snackbarSeverity} openSnackbar={openSnackbar} setOpenSnackbar={setOpenSnackbar}/>
        </>
    )
}

export default Layout;