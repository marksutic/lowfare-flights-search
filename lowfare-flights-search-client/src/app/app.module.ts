import {BrowserModule, Title} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {ButtonModule} from 'primeng/button';
import {RadioButtonModule} from 'primeng/radioButton';
import {InputTextModule} from 'primeng/inputtext';
import {CalendarModule} from 'primeng/calendar';
import {AutoCompleteModule} from "primeng/autocomplete";
import {KeyFilterModule} from 'primeng/keyfilter';
import {DropdownModule} from 'primeng/dropdown';
import {TableModule} from 'primeng/table';

import {AppComponent} from './app.component';
import {SearchMenuComponent} from './search-menu/search-menu.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {MessagesComponent} from './messages/messages.component';
import { TableComponent } from './table/table.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchMenuComponent,
    HeaderComponent,
    FooterComponent,
    MessagesComponent,
    TableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ButtonModule,
    InputTextModule,
    CalendarModule,
    RadioButtonModule,
    KeyFilterModule,
    DropdownModule,
    TableModule,
    AutoCompleteModule
  ],
  providers: [Title],
  bootstrap: [AppComponent]
})
export class AppModule {
}
