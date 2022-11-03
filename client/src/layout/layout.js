import { Button, Container, Fab, Grid, Stack } from "@mui/material";
import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";
import LoaderComponent from "../components/loadingbarComponent";
import SnackBarMessage from "../components/snackbarComponent";
//import Footer from "./footer";
import Gnb from "./gnb";
import Lnb from "./lnb";

const Layout = () => {
    const [openSnackbar, setOpenSnackbar] = useState(false);    
    const [snackbarMessage, setSnackbarMessage] = useState("");    
    const [snackbarSeverity, setSnackbarSeverity] = useState("danger");    
    const [loading, setLoading] = useState(false);
    const [isLogin, setIsLogin] = useState(false);
    const layoutConfig = {
        login : {isLogin, setIsLogin},
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

    useEffect(()=> {
        console.log(isLogin);
    },[isLogin]);

    return (
        <>
            <Stack spacing={1}>
                <Grid container spacing={1}>
                    <Gnb isLogin={isLogin}/>
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