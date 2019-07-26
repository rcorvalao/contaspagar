import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { CoreModule } from './core/core.module';
import { AboutComponent } from './about/about.component';
import { ContasPagarModule } from './contas-pagar/contas-pagar.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    CoreModule,
    // Modulos de recursos
    ContasPagarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
